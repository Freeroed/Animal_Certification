package ru.vlsu.animalcertification.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, ru.vlsu.animalcertification.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, ru.vlsu.animalcertification.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, ru.vlsu.animalcertification.domain.User.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Authority.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.User.class.getName() + ".authorities");
            createCache(cm, ru.vlsu.animalcertification.domain.Animal.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Breed.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.AnimalType.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Vaccine.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.LaboratoryResearch.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Request.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Request.class.getName() + ".documents");
            createCache(cm, ru.vlsu.animalcertification.domain.Region.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Country.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.DocumentType.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Document.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Document.class.getName() + ".requests");
            createCache(cm, ru.vlsu.animalcertification.domain.BorderCrossingPoint.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.PersonData.class.getName());
            createCache(cm, ru.vlsu.animalcertification.domain.Address.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
