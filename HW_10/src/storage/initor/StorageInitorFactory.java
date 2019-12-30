package storage.initor;

public class StorageInitorFactory {
    private StorageInitorFactory(){

    }

    public static StorageInitor getStorageInitor(InitStorageType initStorageType){
        switch (initStorageType){

            case MEMORY: {
                return new InMemoryStorageInitor();
            }
            case TEXT_FILE: {
                return new InTextFileStorageInitor();
            }
            default:{
                throw new RuntimeException("Unknown storage init type " + initStorageType);
            }
        }
    }

}
