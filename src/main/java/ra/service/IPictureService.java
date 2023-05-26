package ra.service;

import ra.model.Pictures;
import ra.model.Song;

import java.util.List;

public interface IPictureService {
    List<Pictures> findAll();
    Pictures findById(int id);
    void deleteById(int id);
    void save(Pictures p);
    Pictures update(Pictures p);
}
