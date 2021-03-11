package tabledriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ParseHelper
{
    private static final class TokenInfo
    {
        public final int tokenIndex;
        public final int tokenLength;

        public TokenInfo(int tokenIndex, int tokenLength)
        {
            this.tokenIndex = tokenIndex;
            this.tokenLength = tokenLength;
        }
    }

    public static String[] splitByTokens(String input, String... tokens)
    {
        List<TokenInfo> tokenIndecies = ParseHelper.scanForTokens(input, tokens);

        if (tokenIndecies.isEmpty()) { return new String[] { input }; }

        AtomicInteger subStringStart = new AtomicInteger(0);
        Stream<String> resultStream = tokenIndecies
            .stream()
            .flatMap(i ->
            {
                String subString = input.substring(subStringStart.get(), i.tokenIndex);
                String tokenString = input.substring(i.tokenIndex, i.tokenIndex + i.tokenLength);
                subStringStart.set(i.tokenIndex + i.tokenLength);
                return Stream.of(subString, tokenString);
            });

        TokenInfo lastToken = tokenIndecies.get(tokenIndecies.size() - 1);
        return Stream.concat(resultStream, Stream.of(input.substring(lastToken.tokenIndex + lastToken.tokenLength)))
            .toArray(String[]::new);
    }

    public static String[] splitByToken(String input, char token)
    {
        String[] splitResults = ParseHelper.splitByTokens(input, String.format("%c", token));
        return IntStream.range(0, splitResults.length)
            .filter(i -> i % 2 == 0)
            .mapToObj(i -> splitResults[i])
            .toArray(String[]::new);
    }

    private static List<TokenInfo> scanForTokens(String input, String... tokens)
    {
        List<TokenInfo> indecies = new ArrayList<>();
        char[] inputChars = input.toCharArray();

        int i = 0;
        while (i < input.length())
        {
            final char currentChar = inputChars[i];
            if (currentChar == '\\') { i++; }
            else if (Stream.of(tokens).anyMatch(t -> t.charAt(0) == currentChar)) 
            { 
                final String substring = input.substring(i);
                int tokenLength = Stream.of(tokens)
                    .filter(substring::startsWith)
                    .map(t -> Integer.valueOf(t.length()))
                    .max(Integer::compare)
                    .orElse(Integer.valueOf(0))
                    .intValue();

                if (tokenLength > 0)
                {
                    indecies.add(new TokenInfo(i, tokenLength));
                    i += (tokenLength - 1);
                }
            }

            i++;
        }

        return indecies;
    }

    private ParseHelper() {}
}
