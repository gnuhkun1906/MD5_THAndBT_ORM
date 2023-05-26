package ra.service;


import ra.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    void deleteById(Long id);
    void save(Song customer);
    Song update(Song customer);
}
