package lotto.model;

import lotto.utils.BonusNumberValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoHandler {

    private List<Lottos> lottos = new ArrayList<>();
    private Lotto winningLottoNumbers;
    private int bonusNumber;

    public void buyLottos(int lottoTickets) {
        for(int num = 0; num < lottoTickets; num++) {
            Lottos lottoNumbers= Lottos.generateLottoNumbers();
            lottos.add(lottoNumbers);
        }
    }

    public String getLottoList() {
        return getLottos().stream()
                .map(lottos -> lottos.getLottoNumbers().toString())
                .collect(Collectors.joining("\n"));
    }

    public List<Lottos> getLottos() {
        return lottos;
    }

    public void inputWinningLottoNumbers(String rawWinningNumbers) {
        List<Integer> winningNumbers = Arrays.stream(rawWinningNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        winningLottoNumbers = new Lotto(winningNumbers);
    }


    public Lotto getWinningLottoNumbers() {
        return winningLottoNumbers;
    }

    public void setBonusNumber(int rawBonusNumber) {
        BonusNumberValidation.validateNumberRange(rawBonusNumber);
        this.bonusNumber = rawBonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }


    public void checkedResult(Lotto winningLotto, Lottos buyLottos) {
        int rankedNumber = checkSameNumber(winningLotto.getNumbers(), buyLottos.getLottoNumbers());
    }

    private int checkSameNumber(List<Integer> winningLottoNumbers, List<Integer> buyLottoNumbers) {
        int count = 0;
        for(int num : buyLottoNumbers) {
            if (winningLottoNumbers.contains(num)) {
                count++;
            }
        }
        return count;
    }
}
