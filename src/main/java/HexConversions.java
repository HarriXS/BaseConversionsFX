import java.util.Arrays;

public class HexConversions {
    public String hexToBinary(String hex) {
        DenaryConversions denConv = new DenaryConversions();

        String[] hexArray = hex.split("");
        StringBuilder binary = new StringBuilder();
        String[] hexLetters = {"A", "B", "C", "D", "E", "F"};

        // Yay more fancy for loops
        for (String digit : hexArray) {
            if (Arrays.asList(hexLetters).contains(digit) || digit.equals("0")) {
                // Yay more fancy switch statements
                switch (digit) {
                    case "A" -> binary.append("1010");
                    case "B" -> binary.append("1011");
                    case "C" -> binary.append("1100");
                    case "D" -> binary.append("1101");
                    case "E" -> binary.append("1110");
                    case "F" -> binary.append("1111");
                    case "0" -> binary.append("0000");
                }
            } else {
                binary.append(denConv.denaryToBinary(digit));
            }
        }

        // Binary Beautification
        int insertCounter = 0;
        for (int j = 0; j < binary.length(); j++) {
            if (j % 4 == 0 && j != 0) {
                binary.insert(j + insertCounter, " ");
                insertCounter++;
            }
        }

        return binary.toString();
    }

    public String hexToDenary(String hex) {
        BinaryConversions binConv = new BinaryConversions();

        String binary = hexToBinary(hex).replaceAll("\\s","");
        return binConv.binaryToDenary(binary);
    }
}
