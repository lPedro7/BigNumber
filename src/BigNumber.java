class BigNumber {

    String numero;

    // Constructor 1
    public BigNumber(String s) {
        this.numero=s;
    }
    // Constructor 2
    public BigNumber(BigNumber b) {
        this.numero=b.numero;
    }
    // Suma
    BigNumber add(BigNumber other) {
        return other;
    }
    // Resta
    BigNumber sub(BigNumber other) {
        return other;
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
        return -1;
    }
    // Torna un String representant el número
    public String toString() {
        return "0";
    }
    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {

        if (other instanceof BigNumber){
            System.out.println("Es big number");

            System.out.println(this.numero);
            System.out.println(((BigNumber) other).numero);
            System.out.println(skipZero((BigNumber) other).numero);
        }

        return false;
    }

    BigNumber skipZero(BigNumber bn){

        BigNumber bnResultat = new BigNumber(bn.numero);

        System.out.println(bnResultat.numero.charAt(0));

       while (bnResultat.numero.charAt(0) == '0' && bnResultat.numero.length()>1){
           bnResultat.numero = bnResultat.numero.substring(1,bnResultat.numero.length()-1);
       }


        return bnResultat;
    }

}
