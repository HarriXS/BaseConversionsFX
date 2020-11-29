public class DenaryConversions {
    public String denaryToBinary(String denaryString) {
        int denary = Integer.parseInt(denaryString);
        StringBuilder binary = new StringBuilder();

        // Very fancy maths shit that will find the length the binary will need to be
        // Unfortunately Math.log() can only handle up to 65535 :(
        double power = Math.ceil(Math.log(denary) / Math.log(2));

        for (int i = 0; i <= power; i++) {
            if (denary - (Math.pow(2, power - i)) >= 0) {   // I know they can be compared directly - this just makes more sense in my head :)
                binary.append("1");
                denary -= Math.pow(2, power - i);
            } else if (! binary.toString().equals("")) {
                    binary.append("0");
            }
        }

        // Add zeros to the beginning until the length is a multiple of four
        while (binary.length() % 4 != 0) {
            binary.insert(0, "0");
        }

        // Add a space every four digits (to make it actually readable lmao)
        int insertCounter = 0;
        for (int j = 0; j < binary.length(); j++) {
            if (j % 4 == 0 && j != 0) {
                binary.insert(j + insertCounter, " ");
                insertCounter++;
            }
        }

        return binary.toString();
    }

    public String denaryToHex(String denary) {
        BinaryConversions binConv = new BinaryConversions();

        String binary = denaryToBinary(denary).replaceAll("\\s","");
        return binConv.binaryToHex(binary);
    }
}
