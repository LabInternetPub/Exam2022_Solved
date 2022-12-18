package cat.tecnocampus.apollofy.persistence;

import cat.tecnocampus.apollofy.domain.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

    @Query("select p from play_list p where p.owner.email like ?1")
    List<PlayList> findUserPlayLists(String email);

}
