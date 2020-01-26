class BigNumber {

    String numero;

    // Constructor 1
    public BigNumber(String s) {
        this.numero = s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {
        this.numero = b.numero;
    }

    // Suma
    BigNumber add(BigNumber other) {

        compareTo(other);

        BigNumber resultat = new BigNumber("");

        int sum;
        int acum = 0;
        for (int i = this.numero.length() - 1; i >= 0; i--) {

            int num1 = Integer.parseInt(String.valueOf(this.numero.charAt(i)));
            int num2 = Integer.parseInt(String.valueOf(other.numero.charAt(i)));

            sum = num1 + num2 + acum;
            acum = 0;
            if (sum > 9) {
                acum = sum / 10;
                sum = sum % 10;
            }


            resultat.numero = sum + resultat.numero;

        }
        if (acum != 0) {
            resultat.numero = acum + resultat.numero;
        }
        return resultat;
    }

    // Resta
    BigNumber sub(BigNumber other) {

        compareTo(other);

        BigNumber resultat = new BigNumber("");


        int res;
        int acum = 0;
        for (int i = this.numero.length() - 1; i >= 0; i--) {

            int num1 = Integer.parseInt(String.valueOf(this.numero.charAt(i)));
            int num2 = Integer.parseInt(String.valueOf(other.numero.charAt(i)));


            if (acum > 0){
                num2+=1;
                acum=0;
            }

            if (num1 < num2){
                num1+=10;
                acum=1;
            }

            res = num1-num2;

            resultat.numero = res + resultat.numero;




        }
        return resultat;
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        return other;
    }

    // Divideix
    BigNumber div(BigNumber other) {
        return other;
    }

    // Arrel quadrada
    BigNumber sqrt() {
        return new BigNumber("1");
    }

    // Potència
    BigNumber power(int n) {
        return new BigNumber("2");
    }

    // Factorial
    BigNumber factorial() {
        return new BigNumber("2");

    }

    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {
        return new BigNumber("2");

    }

    // Compara dos BigNumber. Torna 0 si són iguals, -1
// si el primer és menor i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {

        if (this.numero.length() > other.numero.length()) {

            while (this.numero.length() > other.numero.length()) {
                other.numero = '0' + other.numero;
            }

        } else if (this.numero.length() < other.numero.length()) {

            while (this.numero.length() < other.numero.length()) {
                this.numero = '0' + this.numero;
            }

        }

        if (this.numero.equals(other.numero)) return 0;

        if (this.numero.length() > other.numero.length()) return 1;


        if (this.numero.length() < other.numero.length()) return -1;


        for (int i = 0; i < this.numero.length(); i++) {

            int numPrimer = Integer.parseInt(String.valueOf(this.numero.charAt(i)));

            int numSegon = Integer.parseInt(String.valueOf(other.numero.charAt(i)));


            if (numPrimer > numSegon) {
                return 1;
            } else if (numSegon > numPrimer) {
                return -1;
            }
        }
        return 0;

    }

    // Torna un String representant el número
    public String toString() {
        return this.numero;
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {

        //Comprobamos que el objeto recibido es un objeto tipo BigNumber
        if (other instanceof BigNumber) {

            //Creamos un objeto BigNumber casteando
            BigNumber bb = (BigNumber) other;

            //Comprobamos el caso de que sean todo 0
            if (allZero(this)) {
                if (allZero(bb)) {
                    return true;
                } else return false;
            } else if (allZero(bb)) {
                return false;
            }

            if (this.numero.length() > bb.numero.length()) {

                bb.numero = addZero(bb, this.numero.length());


            } else if (this.numero.length() < bb.numero.length()) {

                this.numero = addZero(this, bb.numero.length());

            }

            //Comparamos
            if (this.numero.equals(bb.numero)) {
                return true;
            }


        }

        return false;
    }


    //Comprobamos si el numero de BigNumber sea todo 0
    boolean allZero(BigNumber bn) {

        for (int i = 0; i < bn.numero.length(); i++) {
            if (bn.numero.charAt(i) != '0') {
                return false;
            }
        }


        return true;
    }

    String addZero(BigNumber bn, int nLong) {

        while (bn.numero.length() < nLong) {
            bn.numero = '0' + bn.numero;
        }

        return bn.numero;
    }

}
