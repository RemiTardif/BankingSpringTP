package com.example.banking.infrastructure.driven.jdbc;

import com.example.banking.domain.model.Client;
import com.example.banking.domain.port.ClientRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Repository
public class JdbcClientRepository implements ClientRepository {

    // NamedParameterJdbcTemplate permet d'utiliser des paramètres nommés (:firstName)
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // RowMapper statique pour convertir les lignes SQL en objets Client
    private static final RowMapper<Client> CLIENT_ROW_MAPPER = (rs, rowNum) ->
            new Client(
                    rs.getString("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
            );

    public JdbcClientRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //Récupère tous les clients de la base de données
    @Override
    public List<Client> findAll() {
        String sql = """
            SELECT id, first_name, last_name 
            FROM clients
            """;
        // Pas de paramètres nécessaires, on passe une Map vide
        return namedParameterJdbcTemplate.query(sql, Map.of(), CLIENT_ROW_MAPPER);
    }

    //Sauvegarde un client dans la base de données
    @Override
    public Client save(Client client) {
        String sql = """
            INSERT INTO clients (id, first_name, last_name) 
            VALUES (:id, :firstName, :lastName)
            """;

        Map<String, Object> params = Map.of(
                "id", client.id(),
                "firstName", client.firstName(),
                "lastName", client.lastName()
        );
        namedParameterJdbcTemplate.update(sql, params);
        return client;
    }

    //Vérifie si un client existe déjà avec ce prénom et nom
    @Override
    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        String sql = """
            SELECT COUNT(*) 
            FROM clients 
            WHERE first_name = :firstName 
            AND last_name = :lastName
            """;
        // Utilisation de Map.of() pour les paramètres
        Map<String, Object> params = Map.of(
                "firstName", firstName,
                "lastName", lastName
        );
        Integer count = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                Integer.class
        );
        return count != null && count > 0;
    }

    @Override
    public boolean existsById(String id) {
        String sql = """
        SELECT COUNT(*) 
        FROM clients 
        WHERE id = :id
        """;

        Map<String, Object> params = Map.of("id", id);

        Integer count = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                Integer.class
        );

        return count != null && count > 0;
    }
}