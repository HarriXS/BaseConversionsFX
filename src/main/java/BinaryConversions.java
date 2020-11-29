public class BinaryConversions {
    public String binaryToDenary(String binary) {
        // Split the binary string into an array of characters
        String[] binaryArray = binary.split("");
        int denary = 0;

        // Very epic maths shit
        for (int i = 0; i < binaryArray.length; i++) {
            if (binaryArray[i].equals("1")) {
                denary += Math.pow(2, binaryArray.length - 1 - i);
            }
        }

        return Integer.toString(denary);
    }

    public String binaryToHex(String binary) {
        // StringBuilder allows you to easily build strings (shocking, isn't it?)
        StringBuilder binaryBuilder = new StringBuilder(binary);

        // Add zeros to the binary string until there are n whole nibbles
        while (binaryBuilder.length() % 4 != 0) {
            binaryBuilder.insert(0, "0");
        }
        binary = binaryBuilder.toString();

        // Split the binary string into an array every 4 characters
        String[] binaryArray = binary.split("(?<=\\G....)");
        int denary;

        // Hooray for StringBuilder
        StringBuilder hex = new StringBuilder();

        // Fancy for loop very nice
        for (String nibble : binaryArray) {
            // Get the denary value of the nibble
            denary = Integer.parseInt(binaryToDenary(nibble));

            if (denary >= 10) {
                // Fancy switch statement very nice
                switch (denary) {
                    case 10 -> hex.append("A");
                    case 11 -> hex.append("B");
                    case 12 -> hex.append("C");
                    case 13 -> hex.append("D");
                    case 14 -> hex.append("E");
                    case 15 -> hex.append("F");
                }
            } else if (denary >= 0) {
                hex.append(denary);
            }
        }

        return hex.toString();
    }
}
