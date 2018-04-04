package com.piper.valley.models.service;
import com.piper.valley.models.common.SearchResult;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreProduct;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

@Service
public class SearchServiceImpl implements SearchService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchResult generalSearch(String queryString) {
        return new SearchResult(queryString, storeProductSearch(queryString), storeSearch(queryString));
    }

	@Override
	public List<StoreProduct> storeProductSearch(String queryString) {
		FullTextEntityManager fullTextEntityManager = getFullTextEntityManager(entityManager);

		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(StoreProduct.class).get();

		// query by keywords
		Query query =
				queryBuilder
						.keyword()
						.fuzzy()
						//.withThreshold(0.8f) default = 0.5
						.withPrefixLength(1)
						.onFields("name", "description", "product.name")
						.boostedTo(5)   //give above more weight
						.andField("product.brand.name")
						.andField("product.company.name")
						.andField("store.name")
						.matching(queryString)
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, StoreProduct.class);

		jpaQuery.limitExecutionTimeTo(500, TimeUnit.MILLISECONDS);

		//TODO (Set a limit for number of result per query, setMax isn't included in jpa, search for alternative.)

		// execute search and return results (sorted by relevance as default)
		List results = jpaQuery.getResultList();

		return results;
	}

	@Override
	public List<Store> storeSearch(String queryString) {
		FullTextEntityManager fullTextEntityManager = getFullTextEntityManager(entityManager);

		QueryBuilder queryBuilder =
				fullTextEntityManager.getSearchFactory()
						.buildQueryBuilder().forEntity(Store.class).get();

		// query by keywords
		Query query =
				queryBuilder
						.keyword()
						.fuzzy()
						//.withThreshold(0.8f) default = 0.5
						.withPrefixLength(1)
						.onFields("name")
						.matching(queryString)
						.createQuery();

		// wrap Lucene query in an Hibernate Query object
		FullTextQuery jpaQuery =
				fullTextEntityManager.createFullTextQuery(query, Store.class);

		jpaQuery.limitExecutionTimeTo(500, TimeUnit.MILLISECONDS);

		//TODO (Set a limit for number of result per query, setMax isn't included in jpa, search for alternative.)

		// execute search and return results (sorted by relevance as default)
		List results = jpaQuery.getResultList();

		return results;
	}
}
