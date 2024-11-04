package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class PrizeResult {

    private final Map<Prize, Integer> prizes;

    public PrizeResult() {
        prizes = new EnumMap<>(Prize.class);
        Arrays.stream(Prize.values())
                .forEach(prize -> prizes.put(prize, 0));
    }

    public void calculatePrizes(WinningLotto winningLotto, Lottos lottos) {
        lottos.lottos().stream()
                .map(lotto -> Prize.determinePrize(
                        lotto.getMatchingCountWith(winningLotto.winningNumbers()),
                        lotto.containsBonusNumber(winningLotto.bonusNumber())))
                .forEach(this::updatePrizeResult);
    }

    private void updatePrizeResult(Prize prize) {
        prizes.put(prize, prizes.get(prize) + 1);
    }
}
