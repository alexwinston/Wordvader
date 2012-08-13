package wordvader;

public class Asserts {
    public static boolean assertArraysEqual(String[][] array1, String[][] array2) {
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (!array1[i][j].equals(array2[i][j]))
                    throw new AssertionError(array1[i][j] + ":" + array2[i][j] + ":[" + i + "][" + j + "]");
            }
        }

        return true;
    }

    public static boolean assertArraysEqual(char[][] array1, char[][] array2) {
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (array1[i][j] != array2[i][j])
                    throw new AssertionError(array1[i][j] + ":" + array2[i][j] + ":[" + j + "][" + i + "]");
            }
        }

        return true;
    }
}
