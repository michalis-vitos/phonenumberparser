package gr.michalisvitos.phoneparser;

import java.util.Map;

public class NumberParser {

    private final Map<String, Integer> callingCodes;
    private final Map<String, String> prefixes;

    public NumberParser(Map<String, Integer> callingCodes, Map<String, String> prefixes) {
        this.callingCodes = callingCodes;
        this.prefixes = prefixes;
    }

    public String parse(String dialledNumber, String userNumber) {

        if (dialledNumber.isEmpty() || userNumber.isEmpty())
            return "";

        // If the number is already in an international format, just return the number
        if (dialledNumber.startsWith("+"))
            return dialledNumber;

        // Get the country from the user's number
        final String country = getCountryFromTelephone(userNumber);

        // international number = plus sign + country code + the rest of the number without the prefix
        return "+" + callingCodes.get(country) + dialledNumber.replaceFirst(prefixes.get(country), "");
    }

    /**
     * Get the country code e.g. GB from a telephone number
     *
     * @param telephone the number should be in an international format e.g. +442079460056
     * @return the country code
     */
    private String getCountryFromTelephone(String telephone) {

        String country = "";
        int prefix = -1;
        String tempPrefix = "";

        // International codes can be up to 4 digits long
        for (int i = 1; i <= 4; i++) {

            tempPrefix += telephone.charAt(i);

            if (callingCodes.containsValue(Integer.valueOf(tempPrefix)))
                prefix = Integer.parseInt(tempPrefix);
        }

        for (Map.Entry<String, Integer> entry : callingCodes.entrySet()) {
            if (entry.getValue() == prefix) {
                country = entry.getKey();
            }
        }

        return country;
    }
}
