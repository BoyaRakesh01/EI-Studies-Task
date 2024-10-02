import java.util.ArrayList;
import java.util.List;

abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void display(String indent);
    public abstract int getSize();
}

class File extends FileSystemComponent {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + name + " (" + size + " KB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + name + "/");
        for (FileSystemComponent child : children) {
            child.display(indent + "  ");
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }
}

public class FileSystemCompositeDemo {
    public static void main(String[] args) {
        Directory root = new Directory("root");

        Directory documents = new Directory("documents");
        documents.add(new File("resume.pdf", 500));
        documents.add(new File("cover_letter.docx", 100));

        Directory pictures = new Directory("pictures");
        pictures.add(new File("vacation.jpg", 2000));
        pictures.add(new File("family.png", 1500));

        root.add(documents);
        root.add(pictures);
        root.add(new File("notes.txt", 10));

        root.display("");
        System.out.println("Total size: " + root.getSize() + " KB");
    }
}
