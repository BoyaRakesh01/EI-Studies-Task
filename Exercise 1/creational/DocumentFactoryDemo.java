interface Document {
    String create();
}

class PDFDocument implements Document {
    @Override
    public String create() {
        return "Creating PDF document";
    }
}

class WordDocument implements Document {
    @Override
    public String create() {
        return "Creating Word document";
    }
}

abstract class DocumentCreator {
    public abstract Document createDocument();

    public void someOperation() {
        Document document = createDocument();
        System.out.println(document.create());
    }
}

class PDFCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}

class WordCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

public class DocumentFactoryDemo {
    public static void main(String[] args) {
        DocumentCreator creator = new PDFCreator();
        creator.someOperation();

        creator = new WordCreator();
        creator.someOperation();
    }
}
