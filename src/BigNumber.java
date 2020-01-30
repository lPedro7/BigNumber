import java.util.Arrays;

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

        addZero(this, other.numero.length());
        addZero(other, this.numero.length());

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

        addZero(this, other.numero.length());
        addZero(other, this.numero.length());

        BigNumber resultat = new BigNumber("");


        int res;
        int acum = 0;
        for (int i = this.numero.length() - 1; i >= 0; i--) {

            int num1 = Integer.parseInt(String.valueOf(this.numero.charAt(i)));
            int num2 = Integer.parseInt(String.valueOf(other.numero.charAt(i)));


            if (acum > 0) {
                num2 += 1;
                acum = 0;
            }

            if (num1 < num2) {
                num1 += 10;
                acum = 1;
            }

            res = num1 - num2;

            resultat.numero = res + resultat.numero;


        }
        return resultat;
    }

    // Multiplica
    BigNumber mult(BigNumber other) {

        BigNumber resultado = new BigNumber("");


        if (this.equals(new BigNumber("0")) || other.equals(new BigNumber("0"))) return new BigNumber("0");

        for (int i = other.numero.length() - 1, cont = 0; i >= 0; i--, cont++) {
            BigNumber suma = new BigNumber("");
            int acumul = 0;

            for (int j = this.numero.length() - 1; j >= 0; j--) {


                int num1 = Integer.parseInt(String.valueOf(other.numero.charAt(i)));
                int num2 = Integer.parseInt(String.valueOf(this.numero.charAt(j)));

                int mult = num1 * num2 + acumul;

                acumul = 0;

                if (mult > 9) {

                    acumul = mult / 10;
                    mult = mult % 10;

                }

                suma.numero = mult + suma.numero;

            }

            for (int j = 0; j < cont; j++) {
                suma.numero = suma.numero + "0";
            }

            if (acumul != 0) {
                suma.numero = acumul + suma.numero;
            }
            resultado.numero = resultado.add(suma).numero;

        }

        return resultado;
    }

    // Divideix
    BigNumber div(BigNumber other) {

        BigNumber resultat = new BigNumber("");

        BigNumber resto = new BigNumber("");

        if (this.compareTo(other) == -1) {
            return new BigNumber("0");
        }

        BigNumber xifraDividir = new BigNumber("");

        for (int i = 0; i < this.numero.length(); i++) {

            xifraDividir.numero += this.numero.charAt(i);

            if (other.compareTo(xifraDividir) == -1) {

                for (int j = 0; j < 11; j++) {

                    BigNumber multiplicacion = new BigNumber(String.valueOf(j)).mult(other);

                    if (multiplicacion.compareTo(xifraDividir) == 1) {
                        resultat.numero += new BigNumber(String.valueOf(j - 1));
                        xifraDividir = xifraDividir.sub(new BigNumber(String.valueOf(j - 1)).mult(other));
                        break;
                    } else if (multiplicacion.compareTo(xifraDividir) == 0) {
                        resultat.numero += new BigNumber(String.valueOf(j));
                        xifraDividir = xifraDividir.sub(multiplicacion);
                        break;
                    }
                }
            } else if (other.compareTo(xifraDividir) == 0) {
                resultat.numero += new BigNumber("1");
                xifraDividir.numero = "0";
            } else if (resultat.numero.length() > 0) {
                resultat.numero += 0;
            }
        }

        resto.numero = xifraDividir.numero;
        return resultat;
    }


    // Arrel quadrada
    BigNumber sqrt() {

        BigNumber resultado = new BigNumber("0");
        BigNumber aux = new BigNumber("");


        if (this.numero.length() % 2 != 0) {
            this.numero = "0" + this.numero;
        }

        System.out.println("Dividimos en grupos de 2 el dividendo : ");

        String[] rQuad = new String[this.numero.length() / 2];

        Arrays.fill(rQuad, "");

        for (int i = 0, cont = 0; i < rQuad.length; i++) {

            for (int j = 0; j < 2; j++, cont++) {
                rQuad[i] += this.numero.charAt(cont);
            }
            System.out.println(i + " - " + rQuad[i]);

        }


        System.out.println("Bajamos los 2 primeros numeros");

        System.out.println("Buscamos un numero que se aproxime sin superar el valor de los primeros 2 numeros");

        for (int i = 0; i < rQuad.length; i++) {
            System.out.println("Entramos en el primer bucle");
            aux.numero+=rQuad[i];

            System.out.println("El valor actual de aux es " + aux);

            for (int j = 0; j < 11; j++) {

              //  BigNumber resX2 = new BigNumber((resultado.mult(new BigNumber("2")).add(new BigNumber(String.valueOf(j)))).mult(new BigNumber(String.valueOf(j))));

                BigNumber resX2 = new BigNumber(resultado.mult(new BigNumber("2")).numero+(j));
                resX2 = resX2.mult(new BigNumber(String.valueOf(j)));


                System.out.println("Valor de resX2 es " +resX2);
                System.out.println("Valor de aux es " + aux);

                if (resX2.compareTo(aux) == 1){
                    System.out.println("El numero inmediatamente mayor a " + aux + " es " + resX2);



                    BigNumber resta = new BigNumber(resultado.mult(new BigNumber("2")).numero+(j-1));

                    resta = resta.mult(new BigNumber(String.valueOf(j-1)));

                    aux = aux.sub(resta);

                    resultado.numero += String.valueOf(j-1);


                    break;
                }else if (resX2.equals(aux)){
                    System.out.println(aux + " y " + resX2 + " son iguales");
                    resultado.numero+=j;
                    aux = aux.sub(resX2);
                    break;
                }

            }



        }
        System.out.println("resultado es " + resultado);


        return resultado;
    }

    // Potència
    BigNumber power(int n) {

        BigNumber resultat = new BigNumber(this.numero);

        for (int i = 1; i < n; i++) {
            resultat = resultat.mult(this);
        }

        return resultat;
    }

    // Factorial
    BigNumber factorial() {

        BigNumber bn = new BigNumber(this.numero);

        int i = 1;

        BigNumber count = new BigNumber(this);

        while (this.compareTo(new BigNumber(String.valueOf(i))) == 1) {

            bn = bn.mult(count.sub(new BigNumber(("1"))));
            count = count.sub(new BigNumber("1"));

            i++;

        }


        return bn;

    }

    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {

        return obtener_mcd(this, other);
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
    @Override
    public String toString() {
        return this.numero;
    }

    // Mira si dos objectes BigNumber són iguals
    @Override
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


    //Funcion para añadir ceros a la izquieda y que queden los numeros con la misma longitud
    String addZero(BigNumber bn, int nLong) {

        while (bn.numero.length() < nLong) {
            bn.numero = '0' + bn.numero;
        }


        return bn.numero;
    }

    BigNumber getResto(BigNumber bn) {

        BigNumber resultat = new BigNumber("");

        resultat = this.sub(bn.mult(this.div(bn)));

        return resultat;
    }

    BigNumber removeZero(BigNumber bn) {

        if (!allZero(bn)) {

            for (int i = 0; bn.numero.charAt(i) == '0'; ) {
                bn.numero = bn.numero.substring(i + 1);
            }
        }
        return bn;
    }

    BigNumber obtener_mcd(BigNumber bn1, BigNumber bn) {


        removeZero(bn1);
        removeZero(bn);

        if (bn.equals(new BigNumber("0"))) {
            return bn1;
        } else if (bn1.equals(new BigNumber("0"))) {
            return bn;
        } else
            return obtener_mcd(bn, bn1.getResto(bn));

    }

}


