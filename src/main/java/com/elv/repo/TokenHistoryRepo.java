package com.elv.repo;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.elv.models.TokenHistory;

@Repository
public interface TokenHistoryRepo extends CrudRepository<TokenHistory,String> {
	//public TokenHistory findByTokenValue(String toneval);
	/*@Modifying
	@Transactional
	@Query("delete from tokenhistory t where t.tokenValue like :token")
	public void deleteToken(@Param("token")String token);*/
}
