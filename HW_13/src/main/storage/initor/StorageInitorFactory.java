package main.storage.initor;

import main.storage.initor.fileInitor.InTextFileStorageInitor;
import main.storage.initor.fileInitor.XmlDomFileDataInitor;
import main.storage.initor.fileInitor.XmlSAXFileInitor;

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
            case XML_FILE:{
                return new XmlDomFileDataInitor();
            }
            case SAX_PARSE:{
                return new XmlSAXFileInitor();
            }
            default:{
                throw new RuntimeException("Unknown main.storage init type " + initStorageType);
            }
        }
    }

}
