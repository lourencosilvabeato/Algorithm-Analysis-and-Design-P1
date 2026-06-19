/* AUTHORS
    Lourenço Beato - 68461
    Tomás Sousa - 68302
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int tests = Integer.parseInt(bf.readLine());

        for(int i = 0; i < tests; i++) {

            String[] tokens = bf.readLine().split(" ");
            int rows = Integer.parseInt(tokens[0]);
            int cols = Integer.parseInt(tokens[1]);
            int cons = Integer.parseInt(tokens[2]);
            int jumps = Integer.parseInt(tokens[3]);

            char[][] map = new char[rows][cols];

            for(int j = 0; j < rows; j++) {

                map[j] = bf.readLine().toCharArray();

            }

          CrystalCastle cc = new CrystalCastle(map, cons, jumps);

            System.out.println(cc.calculatePaths());

        }

    }
}

