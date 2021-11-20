package binsuchbaum;

public class Word implements Comparable<Word> {
    private String content; // das Wort als Zeichenkette
    private int n; // die Anzahl des Auftretens dieses Wortes im Text

    public Word(String s) {
        this.content = s; // s als content übernehmen,
        this.n = 1; // Zähler auf 1 setzen (erstes Auftreten)
    }

    public int frequency() {
        return n;
    }

    public void inc() {
        ++this.n;
    }

    public int compareTo(Word w) {
        return this.content.compareTo(w.content);
    }

    public String toString() {
        // liefert für dieses Wort die Zeichenkette: "Häufigkeit : Wort"
        return (String.format("%d : %s", this.n, this.content));
    }
}