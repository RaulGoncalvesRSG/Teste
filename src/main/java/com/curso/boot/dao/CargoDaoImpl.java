package com.curso.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.curso.boot.model.Cargo;
import com.curso.boot.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	//pagina traz o número da pág q o user está solicitando
	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao){
		int tamanho = 5;								//Qtd de registros para cada pag
		int inicio = (pagina - 1) * tamanho;			//0*5=0 	1*5=5	2*5=10
		
		/*Quando trabalha com paginação, n se trabalha com o n° da pág, mas sim com a pos do 1° registro dql pag
		Se tem 15 registros e cada pág tem T=5, o 1° registro da pág 1 vai ser a pos=0, a 2° pág começa da pos=5*/
		List<Cargo> cargos = getEntityManager()
				.createQuery("select c from Cargo c order by c.nome " + direcao, Cargo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		//Se tamanho=5: 15/5 = 3	  Se tamanho=5: 16 + (5-1) = 20/4 = 4      Se tamanho=3: 16 + (3-1) = 18/3 = 6
		long totalRegistros = contarPaginas();
		long totalPaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalPaginas, direcao, cargos);
	}

	private long contarPaginas() {
		return getEntityManager().createQuery("select count(*) from Cargo", Long.class).getSingleResult();
	}
}
