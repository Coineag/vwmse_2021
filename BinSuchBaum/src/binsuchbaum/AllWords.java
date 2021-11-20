package binsuchbaum;

// AllWords.java

public class AllWords {
    private BSBaum<Word> words;

    public AllWords() {
        words = new BSBaum<Word>();
    }

    public void register(String s) {
        Word tmp = new Word(s);
        // holen des Words als Knoten
        Knoten<Word> tmpSec = words.suche(tmp);
        // Prüfen ob das Wort exist
        if (tmpSec != null)
            tmpSec.inhalt.inc();
        words.einfuegen(tmp);
    }

    public void printWords() {
        words.ausgabeVorw();
    }
}