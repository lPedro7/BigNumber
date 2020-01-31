import java.util.Arrays;

// Esta clase es utilizada para almacenar y calcular números extremadamente grandes que no son soportados
// por las variables numéricas habituales con un único atributo que sería el propio número.
class BigNumber {

    String numero;

    // Constructor 1. Creamos el objeto a partir de un String con el valor del número
    public BigNumber(String s) {
        this.numero = s;
    }

    // Constructor 2. Creamos el objeto a partir de otro objeto BigNumber
    public BigNumber(BigNumber b) {
        this.numero = b.numero;
    }

    // Suma. Realizamos la suma entre 2 objetos BigNumber
    BigNumber add(BigNumber other) {

        //Igualamos la longitud de ambos numeros, al más corto, le añadiremos tantos 0
        // a la izquierda hasta que iguale la longitud del otro

        if (this.numero.length() > other.numero.length()) {
            addZero(other, this.numero.length());
        } else {
            addZero(this, other.numero.length());
        }

        //Creamos un objeto que almacenará el resultado, y dos variables int que almacenarán
        // variables para calcular la suma
        BigNumber resultat = new BigNumber("");

        int acum = 0;

        for (int i = this.numero.length() - 1; i >= 0; i--) {

            //Recorremos el número de izquierda a derecha para hacer los cálculos

            int num1 = Integer.parseInt(String.valueOf(this.numero.charAt(i)));
            int num2 = Integer.parseInt(String.valueOf(other.numero.charAt(i)));

            //Sumamos los dos numeros, y sumamos el aculumativo en el caso de que en la anterior suma, el resultante
            //hubiera sido mayor a 9
            int sum = num1 + num2 + acum;
            acum = 0;

            //Si el resultado es mayor a 9, ponemos solo la unidad de la operacion y el resto lo almacenamos en la variable
            // acum
            if (sum > 9) {
                acum++;
                sum = sum - 10;
            }

            //Al resultado final le añadimos la parte unitaria de la operacion

            resultat.numero = sum + resultat.numero;

        }

        //Si cuando terminamos las operaciones, acumul es diferente a 0, tendremos que añadirlo al resultado,
        // ya que sino, la operación sería incorrecta
        if (acum != 0) {
            resultat.numero = acum + resultat.numero;
        }
        return resultat;
    }

    // Resta. Realizamos la resta entre 2 objetos BigNumber
    BigNumber sub(BigNumber other) {

        //Igualamos la longitud de ambos numeros, al más corto, le añadiremos tantos 0
        // a la izquierda hasta que iguale la longitud del otro

        if (this.numero.length() > other.numero.length()) {
            addZero(other, this.numero.length());
        } else {
            addZero(this, other.numero.length());
        }

        //De igual manera que hacemos con la suma, en la resta tenemos un objeto BigNumber que devolverá
        // el resultado de la operación, y dos variables int que servirán para realizar la operación.
        BigNumber resultat = new BigNumber("");


        int res;
        int acum = 0;


        // Recorreremos el String para realizar la operación carácter por carácter
        for (int i = this.numero.length() - 1; i >= 0; i--) {


            // Asignamos el carácter correspondiente de la operacion a dos variables int
            int num1 = Integer.parseInt(String.valueOf(this.numero.charAt(i)));
            int num2 = Integer.parseInt(String.valueOf(other.numero.charAt(i)));

            // En el caso de que el primer número sea menor al segundo, deberemos sumarle 10, ya que así seguro que
            // será mayor, posteriormente esto que hemos añadido, lo deberemos restar al siguiente número
            if (acum > 0) {
                num2 += 1;
                acum = 0;
            }

            if (num1 < num2) {
                num1 += 10;
                acum = 1;
            }

            //Realizamos la resta y añadimos el valor al resultado
            res = num1 - num2;

            resultat.numero = res + resultat.numero;


        }

        return resultat;
    }

    // Multiplica. Realizamos la multiplicación entre dos objetos BigNumber
    BigNumber mult(BigNumber other) {

        //Creamos el objeto que contendrá el resultado de la operación
        BigNumber resultado = new BigNumber("");

        //Si uno de los dos valores es 0, devolveremos 0 directamente
        if (this.equals(new BigNumber("0")) || other.equals(new BigNumber("0"))) return new BigNumber("0");


        //En este bucle añadimos una variable llamada "cont", que sirve para hacer el escalado cuando operamos
        // añadiendo 0 a la derecha
        for (int i = other.numero.length() - 1, cont = 0; i >= 0; i--, cont++) {

            // El objeto suma contendrá el resultado de la multiplicación de cada unidad de uno de los factores,
            // una vez que cambiemos de unidad se reseteará
            BigNumber suma = new BigNumber("");

            //Para la variable acumul seguiremos la misma lógica que en la suma, cuando supera las 9 unidades
            // añadiremos el deceno a la siguiente operación
            int acumul = 0;

            //Empezamos a operar
            for (int j = this.numero.length() - 1; j >= 0; j--) {


                //Cogemos las primeras unidades, en este caso, num1 cambiará con el bucle anterior
                int num1 = Integer.parseInt(String.valueOf(other.numero.charAt(i)));
                int num2 = Integer.parseInt(String.valueOf(this.numero.charAt(j)));

                //Realizamos la operación sumandole el acumulativo si tuviera alguno
                int mult = num1 * num2 + acumul;

                acumul = 0;

                if (mult > 9) {

                    acumul = mult / 10;
                    mult = mult % 10;

                }

                //Añadimos al objeto suma el resultado de esta operación
                suma.numero = mult + suma.numero;

            }

            for (int j = 0; j < cont; j++) {
                suma.numero = suma.numero + "0";
            }

            if (acumul != 0) {
                suma.numero = acumul + suma.numero;
            }
            //Una vez acabada las operaciones con la unidad, añadimos el resutlado actual al final, y seguimos operando
            resultado.numero = resultado.add(suma).numero;

        }

        return resultado;
    }

    // Divide. Hacemos la divisón entre dos objetos BigNumber
    BigNumber div(BigNumber other) {

        //Creamos el objeto que contendrá el resultado de la operación
        BigNumber resultat = new BigNumber("");

        // En caso de que el dividendo sea menor al divisor, automáticamente devolveremos 0
        if (this.compareTo(other) == -1) {
            return new BigNumber("0");
        }

        //El procedimiento para realizar la division es ir bajando del dividendo hasta que este sea mayor al
        // divisor, en el objeto xifraDividir almacenaremos estos numeros que iremos bajando
        BigNumber xifraDividir = new BigNumber("");

        //La división habrá terminado una vez hayamos recorrido el dividendo
        for (int i = 0; i < this.numero.length(); i++) {

            //Bajamos un número del dividendo
            xifraDividir.numero += this.numero.charAt(i);

            //Si con este número que hemos bajado, xifraDividir es mayor al divisor, podemos empezar a operar
            if (other.compareTo(xifraDividir) == -1) {

                // Vamos probando números hasta encontrar el , multiplicándolo al divisor, se acerque más
                // sin pasarse al dividendo
                for (int j = 0; j < 11; j++) {

                    BigNumber multiplicacion = new BigNumber(String.valueOf(j)).mult(other);

                    // Con esta comprobación, una vez que nos pasemos significará que el número que más
                    // se aproxima al dividendo es una unidad menos que la actual
                    if (multiplicacion.compareTo(xifraDividir) == 1) {

                        //Añadimos al resultado el valor anterior al actual y restamos a xifraDividir el valor
                        // de dicho valor para poder continuar operando
                        resultat.numero += new BigNumber(String.valueOf(j - 1));
                        xifraDividir = xifraDividir.sub(new BigNumber(String.valueOf(j - 1)).mult(other));
                        break;

                        // Cabe la posibilidad de que el resultado de la operación sea igual al dividendo,
                        // en cuyo caso añadiríamos el valor actual y restaríamos eso a xifraDividir
                    } else if (multiplicacion.compareTo(xifraDividir) == 0) {
                        resultat.numero += new BigNumber(String.valueOf(j));
                        xifraDividir = xifraDividir.sub(multiplicacion);
                        break;
                    }

                }
                // En el caso de que el divisor y xifraDividir sean iguales, añadiríamos un 1 y nos evitariamos
                // hacer todas las comprobaciones
            } else if (other.compareTo(xifraDividir) == 0) {
                resultat.numero += new BigNumber("1");
                xifraDividir.numero = "0";

                // Si no se cumple ninguno de los casos anteriores, añadiríamos un 0 al resultado para proceder a
                // bajar el siguiente número
            } else {
                resultat.numero += 0;
            }
        }

        return resultat;
    }


    // Raíz Cuadrada. Realizamos la raíz cuadrada de un objeto BigNumber
    BigNumber sqrt() {

        // Creamos dos objetos BigNumber, resultado, como su nombre indica almacenará el resultado final de la operación
        // y aux será utilizado de manera parecida a la división, ya que para la raíz cuadrada deberemos de ir bajando
        // números, pero esta vez, de dos en dos

        BigNumber resultado = new BigNumber("0");
        BigNumber aux = new BigNumber("");

        // Tenemos que dividir el número en grupos de 2, así que si la longitud del número es impar,
        // añadiremos un 0 a la izquierda

        if (this.numero.length() % 2 != 0) {
            this.numero = "0" + this.numero;
        }

        // Creamos el array donde irán almacenados los números y los dejamos vacíos para, posteriormente añadirle
        // los valores correspondientes

        String[] rQuad = new String[this.numero.length() / 2];

        Arrays.fill(rQuad, "");

        for (int i = 0, cont = 0; i < rQuad.length; i++) {

            for (int j = 0; j < 2; j++, cont++) {
                rQuad[i] += this.numero.charAt(cont);
            }

        }

        // Añadiremos a aux los siguientes 2 valores y empezaremos a operar

        for (int i = 0; i < rQuad.length; i++) {
            aux.numero += rQuad[i];

            // Tenemos que buscar un numero que, añadiéndolo a la derecha del resultado por 2,  multiplicándolo por el mismo
            // se acerque lo máximo posible a aux sin sobrepasarlo.

            for (int j = 0; j < 11; j++) {

                //resX2 almacenará el resultado multiplicado por 2, añadiendo  a la derecha el valor que estamos
                // probando ahora, multiplicado por ese mismo valor

                BigNumber resX2 = new BigNumber(resultado.mult(new BigNumber("2")).numero + (j));
                resX2 = resX2.mult(new BigNumber(String.valueOf(j)));

                // Estas comprobaciones son exactamente igual a las de la división, si supera el valor de aux
                // añadiremos el valor anterior al resultado y restaremos a aux dicho valor
                if (resX2.compareTo(aux) == 1) {

                    BigNumber resta = new BigNumber(resultado.mult(new BigNumber("2")).numero + (j - 1));

                    resta = resta.mult(new BigNumber(String.valueOf(j - 1)));

                    aux = aux.sub(resta);

                    resultado.numero += String.valueOf(j - 1);


                    break;
                    // En caso de que sea igual a aux resultado es el valor actual y restamos a aux el valor
                } else if (resX2.equals(aux)) {
                    resultado.numero += j;
                    aux = aux.sub(resX2);
                    break;
                }

            }


        }

        return resultado;
    }

    // Potencia. Realizamos la potencia de un numero BigNumber elevado a un int
    BigNumber power(int n) {

        // Creamos un objeto BigNumber que contendrá el resultado que inicialmente tendrá el valor del objeto
        // que recibamos
        BigNumber resultat = new BigNumber(this.numero);

        //Hacemos un bucle tantas veces como nos indiquen y multiplicaremos el resultado por el objeto recibido
        for (int i = 1; i < n; i++) {
            resultat = resultat.mult(this);
        }

        return resultat;
    }

    // Factorial. Realizar el factorial de un objeto BigNumber
    BigNumber factorial() {

        // Creamos un objeto BigNumber que inicialmente tendrá el contenido del objeto BigNumber recibido
        BigNumber resultado = new BigNumber(this.numero);

        // Mientras el objeto recibido sea mayor a la variable i, tendremos que ir multiplicándolo

        int i = 2;


        // Tenemos un objeto BigNumber que hará de contador que recibe el valor del objeto recibido
        BigNumber count = new BigNumber(this);

        while (this.compareTo(new BigNumber(String.valueOf(i))) == 1) {

            // El resultado será igual a la multiplicación de contador restado 1 hasta que contador tenga el valor de 2
            resultado = resultado.mult(count.sub(new BigNumber(("1"))));
            count = count.sub(new BigNumber("1"));

            i++;

        }


        return resultado;

    }

    // MCD. Realizar el máximo común divisor entre dos objetos BigNumber
    BigNumber mcd(BigNumber other) {

        // Para realizar esta operación, hacemos uso del Teorema de Euclides, por lo que será necesario hacer un
        // proceso recursivo del programa

        // Eliminamos los 0 que puedan tener los dos objetos BigNumber recibidos, ya que sino la operación
        // se retrasaría muchísimo más de lo normal
        removeZero(this);
        removeZero(other);

        // En el caso de que uno de los valores sea 0, devolveremos el otro de los objetos que tenemos
        if (other.equals(new BigNumber("0"))) {
            return this;
        } else if (this.equals(new BigNumber("0"))) {
            return other;
            // Si ninguno es 0, tenemos que obtener el resto de la division del primer objeto entre el segundo hasta
            // conseguir que uno de los dos tenga el valor 0, el otro objeto tendra el valor esperado
        } else
            return other.mcd(this.getResto(other));

    }

    // Compara dos BigNumber. Devuelve 0 sin los dos objetos son iguales, -1 si el primero es menor, y devuelve
    //  1 si el segundo es menor
    public int compareTo(BigNumber other) {

        // En el caso de que uno de los objetos sea más largo que otro, tendremos que igualarlo, por lo tanto
        // llamamos a una función que añade tantos 0 como le pasemos

        if (this.numero.length() > other.numero.length()) {

            addZero(other, this.numero.length());

        } else if (this.numero.length() < other.numero.length()) {

            addZero(this, other.numero.length());
        }

        // Llamamos a equals para comprobar si son el mismo número, es el resultado es true, devolvemos 0
        if (this.numero.equals(other.numero)) return 0;

        // Una vez que hemos comprobado que no son iguales,
        // iremos comprobando unidad por unidad cual de los dos es mayor
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

    // Devuelve un String que representa el número del objeto
    @Override
    public String toString() {
        return this.numero;
    }


    // Mira si dos objetos BigNumber son iguales
    @Override
    public boolean equals(Object other) {


        //Comprobamos que el objeto recibido es un objeto tipo BigNumber
        if (other instanceof BigNumber) {

            //Creamos un objeto BigNumber casteando
            BigNumber bb = (BigNumber) other;

            //Comprobamos si alguno de los numeros está compuesto únicamente por 0
            if (allZero(this)) {

                if (allZero(bb)) {

                    return true;

                } else {

                    return false;

                }

            } else if (allZero(bb)) {

                return false;

            }

            // Comprobamos la longitud para igualarla y poder comprobar el número carácter por carácter
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


    // Comprobamos si el numero de BigNumber sea todo 0
    boolean allZero(BigNumber bn) {

        for (int i = 0; i < bn.numero.length(); i++) {
            if (bn.numero.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }


    // Funcion para añadir ceros a la izquieda y que queden los numeros con la misma longitud
    String addZero(BigNumber bn, int nLong) {

        while (bn.numero.length() < nLong) {
            bn.numero = '0' + bn.numero;
        }


        return bn.numero;
    }

    // Función que obtiene el resto a partir de una división.
    BigNumber getResto(BigNumber bn) {

        return new BigNumber(this.sub(bn.mult(this.div(bn))));
    }

    //Función que elimina los 0 a la izquierda de un objeto BigNumber
    BigNumber removeZero(BigNumber bn) {

        if (!allZero(bn)) {

            for (int i = 0; bn.numero.charAt(i) == '0'; ) {
                bn.numero = bn.numero.substring(i + 1);
            }
        }
        return bn;
    }

}


