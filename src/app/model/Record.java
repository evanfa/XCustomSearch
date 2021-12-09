package app.model;

public class Record {

    public int indexId;
    public String fileName;
    public String filePath;
    public boolean selectedId;
    
    public int getIndexId() {
		return indexId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public boolean isSelectedId() {
		return selectedId;
	}

	public Record(int index, String fileName, String filePath){
        this.indexId =index;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Record(int index, String fileName, String filePath,boolean flagSelected){
        this.indexId =index;
        this.fileName = fileName;
        this.filePath = filePath;
        this.selectedId = flagSelected;
    }

}