package numbers.numbers;

import numbers.enumerator.Properties;

public class MagicNumber {

    long number;
    long process;

    Properties even;
    Properties odd;
    Properties buzz;
    Properties duck;
    Properties palindromic;
    Properties gapful;
    Properties spy;
    Properties square;
    Properties sunny;
    Properties jumping;
    Properties happy;
    Properties sad;

    public MagicNumber (long number) {
        this.number = number;
        this.buzz = buzz(number);
        this.even = even(number);
        this.odd = odd(number);
        this.duck = duck(number);
        this.palindromic = palindromic(number);
        this.gapful = gapful(number);
        this.spy = spy(number);
        this.square = square(number);
        this.sunny = sunny(number);
        this.jumping = jumping(number);
        happyOrSad();

    }

    public MagicNumber (long number, long process) {
        this.number = number;
        this.process = process;
        this.buzz = buzz(number);
        this.even = even(number);
        this.odd = odd(number);
        this.duck = duck(number);
        this.palindromic = palindromic(number);
        this.gapful = gapful(number);
        this.spy = spy(number);
        this.square = square(number);
        this.sunny = sunny(number);
        this.jumping = jumping(number);
        happyOrSad();

    }


    public long getNumber() {
        return number;
    }

    public long getProcess() {
        return process;
    }

    public Properties getEven() {
        return even;
    }

    public Properties getOdd() {
        return odd;
    }

    public Properties getBuzz() {
        return buzz;
    }

    public Properties getDuck() {
        return duck;
    }

    public Properties getPalindromic() {
        return palindromic;
    }

    public Properties getGapful() {
        return gapful;
    }

    public Properties getSpy() {
        return spy;
    }

    public Properties getSquare() {
        return square;
    }

    public Properties getSunny() {
        return sunny;
    }

    public Properties getJumping() {
        return jumping;
    }

    public Properties getHappy() {
        return happy;
    }

    public Properties getSad() {
        return sad;
    }


    private Properties buzz(long number) {
        if (number % 7 == 0 || number % 10 == 7)  {
            return Properties.BUZZ;
        } else {
            return null;
        }
    }

    private Properties odd(long number) {
        return number % 2 > 0 ? Properties.ODD : null;
    }

    private Properties even(long number) {
        return number % 2 == 0 ? Properties.EVEN : null;
    }

    private Properties duck(long number) {
        String num = String.valueOf(number);
        return num.contains("0") ? Properties.DUCK : null;
    }

    private Properties palindromic(long number) {
        long tempNumber = number;
        long reversedNumber = 0;
        long remaining;

        while (tempNumber > 0) {
            remaining = tempNumber % 10;
            reversedNumber = reversedNumber * 10 + remaining;
            tempNumber /= 10;
        }
        return number == reversedNumber ? Properties.PALINDROMIC : null;
    }

    private Properties gapful(long number) {
        long tempNumber = number;
        long lastDigit = number % 10;
        long firstDigit = 0;
        if (number > 99) {
            while (tempNumber > 0) {
                firstDigit = tempNumber % 10 * 10;
                tempNumber  /= 10;
            }
        } else {
            return null;
        }

        long diviNumber = firstDigit + lastDigit;
        return (number % diviNumber == 0) ? Properties.GAPFUL : null;
    }

    private Properties spy(long number) {
        long tempNumber = number;
        long sum = 0;
        long product = 1;

        while (tempNumber > 0) {
            sum += tempNumber % 10;
            product *= tempNumber % 10;
            tempNumber = tempNumber / 10;
        }

        return sum == product ? Properties.SPY : null;
    }

    private Properties square(long number) {
        double x = Math.sqrt(number);
        return x == (int) x ? Properties.SQUARE : null;
    }

    private Properties sunny(long number) {
        double x = Math.sqrt(number + 1);
        return x == (int) x ? Properties.SUNNY : null;
    }

    private Properties jumping(long number) {

        if (number > 0 && number < 10) {
            return Properties.JUMPING;
        }

        long tempNumber = number;
        long lastDigit = number % 10;
        boolean isJumping = false;
        while (tempNumber > 9) {
            tempNumber = tempNumber / 10;
            if (Math.abs(tempNumber % 10 - lastDigit) != 1) {
                isJumping = false;
                break;
            } else {
                isJumping = true;
            }
            lastDigit = tempNumber % 10;
        }
        return (isJumping) ? Properties.JUMPING : null;
    }

    private long happyChecker(long number) {
        long tempNumber = number;
        long sum = 0;

        while (tempNumber > 0){
            while (tempNumber > 0) {
                sum += (tempNumber % 10) * (tempNumber % 10);
                tempNumber = tempNumber / 10;
            }
        }
        return sum;

    }

    private void happyOrSad(){
        long result = this.number;

        while (result != 1 && result != 4) {
            result = happyChecker(result);
        }

        if (result == 1) {
            this.happy = Properties.HAPPY;
            this.sad = null;
        } else {
            this.sad = Properties.SAD;
            this.happy = null;
        }

    }
}
