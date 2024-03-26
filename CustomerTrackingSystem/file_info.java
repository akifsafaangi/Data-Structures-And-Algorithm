public class file_info {
    private String[] infos;
    private static int length = 0; 
    private int size;
    public file_info (String[] parts) {
        infos = new String[10];
        for(int i = 0;i < parts.length;i++) {
            infos[i] = parts[i];
        }
        length++;
        size = parts.length;
    }
    public static int getLength() {return length;}
    public String[] getContent() {return infos;}
    public int getSize() {return size;}
}