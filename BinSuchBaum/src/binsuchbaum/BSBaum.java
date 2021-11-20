package binsuchbaum;

class Knoten<E extends Comparable<E>> {
    E inhalt;
    Knoten<E> o, l, r;

    Knoten(E el) {
        inhalt = el;
    }

    Knoten(E el, Knoten<E> oben) {
        inhalt = el;
        o = oben;
    }

    public String toString() {
        return inhalt.toString();
    }
}

class BSBaum<E extends Comparable<E>> {
    private Knoten<E> wurzel = null;

    boolean istLeer() {
        return wurzel == null;
    }

    void einfuegen(E... e) {
        for (E el : e)
            einfuegen(el);
    }

    void einfuegen(E e) {
        if (wurzel == null)
            wurzel = new Knoten<E>(e);
        else
            rekEinfuegen(e, wurzel);
    }

    void rekEinfuegen(E e, Knoten<E> k) {
        if (e.compareTo(k.inhalt) == 0)
            return;
        if (e.compareTo(k.inhalt) < 0)
            if (k.l == null)
                k.l = new Knoten<E>(e, k);
            else
                rekEinfuegen(e, k.l);
        else if (k.r == null)
            k.r = new Knoten<E>(e, k);
        else
            rekEinfuegen(e, k.r);
    }

    Knoten<E> sucheMin() {
        return sucheMin(wurzel);
    }

    Knoten<E> sucheMin(Knoten<E> kp) {
        if (kp == null)
            return kp;
        while (kp.l != null)
            kp = kp.l;
        return kp;
    }

    Knoten<E> sucheMax() {
        return sucheMax(wurzel);
    }

    Knoten<E> sucheMax(Knoten<E> kp) {
        if (kp == null)
            return kp;
        while (kp.r != null)
            kp = kp.r;
        return kp;
    }

    Knoten<E> nachfolger(Knoten<E> kp) {
        if (kp == null)
            return kp;
        if (kp.r != null)
            return sucheMin(kp.r);
        Knoten<E> oben = kp.o;
        while ((oben != null) && (oben.r == kp)) {
            kp = oben;
            oben = kp.o;
        }
        return oben;
    }

    Knoten<E> vorgaenger(Knoten<E> kp) {
        if (kp == null)
            return kp;
        if (kp.l != null)
            return sucheMax(kp.l);
        Knoten<E> oben = kp.o;
        while ((oben != null) && (oben.l == kp)) {
            kp = oben;
            oben = kp.o;
        }
        return oben;
    }

    void loescheKnoten(Knoten<E> kp) {
        if (kp == null)
            return;
        if (kp.l == null || kp.r == null)
            loesche1(kp);
        else
            loesche2(kp);
    }

    void loesche1(Knoten<E> sohn) { // Loescht Knoten mit max. einem Sohn
        // Bestimme den Enkel - kann null sein
        Knoten<E> enkel = (sohn.l == null) ? sohn.r : sohn.l;
        if (sohn == wurzel) {
            wurzel = enkel;
            wurzel.o = null;
            return;
        }
        // Ab hier ist klar: Vater existiert
        Knoten<E> vater = sohn.o;
        // Verbinde Vater zum Enkel
        if (vater.l == sohn)
            vater.l = enkel;
        else
            vater.r = enkel;
        // Verbinde Enkel zum Vater
        if (enkel != null)
            enkel.o = vater;
    }

    void loesche2(Knoten<E> kp) {
        Knoten<E> min = sucheMin(kp.r);
        kp.inhalt = min.inhalt; // Kopiere Inhalt nach oben
        loesche1(min);
    }

    Knoten<E> suche(E e) {
        return rekSuche(e, wurzel);
    }

    Knoten<E> rekSuche(E e, Knoten<E> k) {
        if (k == null)
            return null;
        if (e.compareTo(k.inhalt) == 0)
            return k;
        if (e.compareTo(k.inhalt) < 0)
            return rekSuche(e, k.l);
        else
            return rekSuche(e, k.r);
    }

    void ausgabeVorw() {
        System.out.println("--------- Ausgabe vorwaerts -------------");
        Knoten<E> kp = sucheMin();
        while (kp != null) {
            System.out.println(kp);
            kp = nachfolger(kp);
        }
        System.out.println();
    }

    void ausgabeRueckw() {
        System.out.println("--------- Ausgabe rueckwaerts -------------");
        Knoten<E> kp = sucheMax();
        while (kp != null) {
            System.out.println(kp);
            kp = vorgaenger(kp);
        }
        System.out.println();
    }
}