/* AUTHORS
    Lourenço Beato - 68461
    Tomás Sousa - 68302
*/
public class CrystalCastle {

    private char[][] map;

    private int rows;
    private int cols;
    private int consecutive;
    private int jumps;

    private int[][][][] sol;

    private static final int MOD = 1000000007;

    private static final char NO_DIAGONAL = 'X';
    private static final char NO_JUMPS = 'J';
    private static final char NO_STEP = '#';

    public CrystalCastle(char[][] map, int cons, int maxJump) {

        this.map = map;

        this.rows = map.length;
        this.cols = map[0].length;

        // número de saltos totais nunca pode exceder o número de linhas-1
        this.jumps = Math.min(maxJump, rows-1);

        // número de saltos consecutivos nunca pode exceder o número máximo de saltos
        this.consecutive = Math.min(cons, jumps);

        // submatriz dos saltos tem de ter coluna e linha correspondentes a 0 saltos
        this.sol = new int[rows][cols][consecutive+1][jumps+1];
    }

    public int calculatePaths() {

        /*
        CASO BASE
        percorre a linha de baixo do map (da direita para a esquerda), preenchendo as submatrizes com 1s
        caso encontre um #, para de preencher (deixando as outras submatrizes com o valor default 0)
         */

        int i = cols-1;
        while(i >= 0 && map[rows-1][i] != NO_STEP) {

            for (int j = 0; j <= consecutive; j++) {
                for (int k = 0; k <= jumps; k++) {
                    sol[rows-1][i][j][k] = 1;
                }
            }
            i--;
        }

        // bottom-up approach, começa pelo destino
       for(int a = rows-2; a >= 0; a--) {
           for(int b = cols-1; b >= 0; b--) {

               char tile = map[a][b];
               if(tile == NO_STEP) {
                   continue;
               }

               for(int c = 0; c <= consecutive; c++) {
                   for(int d = 0; d <= jumps; d++) {

                       int sum = 0;
                       // D
                       sum = (sum + sol[a+1][b][0][d]) % MOD;

                       // R
                       if(b < cols-1)
                           sum = (sum + sol[a][b+1][0][d]) % MOD;

                       if(tile != NO_JUMPS && c < consecutive && d < jumps) {

                           // DD
                           if (a < rows-2)
                               sum = (sum+sol[a+2][b][c+1][d+1]) % MOD;

                           if(tile != NO_DIAGONAL) {

                               // LD
                               if (a < rows-1 && b > 0)
                                   sum = (sum + sol[a+1][b-1][c+1][d+1]) % MOD;

                               // RD
                               if (a < rows -1 && b < cols-1)
                                   sum = (sum + sol[a+1][b+1][c+1][d+1]) % MOD;

                           }
                       }

                       sol[a][b][c][d] = sum;
                   }
               }
           }
       }

        return sol[0][0][0][0];
    }
}
