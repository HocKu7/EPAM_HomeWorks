package ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.common.business.repo;

public class IndexNotFound extends Exception {
    public  IndexNotFound(){
        super("Index not found");
    }
    public  IndexNotFound(Exception e){
        super(e);
    }
}
