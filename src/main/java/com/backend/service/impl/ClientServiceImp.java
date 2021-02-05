package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Client;
import com.backend.entity.Gender;
import com.backend.models.ClientRequest;
import com.backend.models.ClientResponse;
import com.backend.repository.IClientRepository;
import com.backend.repository.IGendersRepository;
import com.backend.service.IClientService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ClientServiceImp implements IClientService {

    private final IClientRepository clientRepo;

    private final IGendersRepository genderRepo;

    public ClientServiceImp(IClientRepository clientRepo, IGendersRepository genderRepo) {
        this.clientRepo = clientRepo;
        this.genderRepo = genderRepo;
    }

    @Transactional(readOnly = true)
    public ClientResponse findById(Long id) {
        Client client = clientRepo.findById(id).orElse(null);
        return new ClientResponse(client);
    }

    @Transactional
    public ClientResponse save(ClientRequest clientRequest) {
        Gender gender = genderRepo.findById(clientRequest.getGender_id()).orElse(null);
        Client client = new Client(clientRequest, gender);
        Client result = clientRepo.save(client);
        return new ClientResponse(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    @Transactional
    public ClientResponse update(Long id, ClientRequest clientRequest) {
        Client client = clientRepo.findById(id).orElse(null);
        Date fecha = new Date();
        Gender gender = genderRepo.findById(clientRequest.getGender_id()).orElse(null);
        client.setActive(clientRequest.getActive());
        client.setAddress(clientRequest.getAddress());
        client.setAlternativeAddress(clientRequest.getAlternative_adress());
        client.setPhone(clientRequest.getPhone());
        client.setAlternativePhone(clientRequest.getAlternative_phone());
        client.setAreaCode(clientRequest.getArea_code());
        client.setEmail(clientRequest.getEmail());
        client.setClientType(clientRequest.getClient_type());
        client.setCuitCuil(clientRequest.getCuit_cuil());
        client.setDocumentType(clientRequest.getDocument_type());
        client.setIDnumber(clientRequest.getDocument());
        client.setFirstName(clientRequest.getFirstname());
        client.setLastName(clientRequest.getLastname());
        client.setGenderId(gender);
        client.setSocialReason(clientRequest.getSocial_reason());
        client.setZipCode(clientRequest.getZipcode());
        client.setProductService(clientRequest.getProduct_service());
        //?
        client.setCity(clientRequest.getCity());
        client.setCountry(clientRequest.getCountry());
        client.setUpdateAt(fecha);
        Client result = clientRepo.save(client);
        return new ClientResponse(result);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        List<Client> client = clientRepo.findAll();
        List<ClientResponse> res = new ArrayList<>();
        for (Client clientAll : client) {
            ClientResponse clientR = new ClientResponse();
            EntyToModel(clientAll, clientR);
            res.add(clientR);
        }
        return res;
    }


    public Client findByIdClient(Long id) {

        return clientRepo.findById(id).orElse(null);
    }

    private void EntyToModel(Client clientAll, ClientResponse clientResp) {
        clientResp.setId(clientAll.getId());
        clientResp.setClientType(clientAll.getClientType());
        clientResp.setDocument_type(clientAll.getDocumentType());
        clientResp.setDocument(clientAll.getIDnumber());
        clientResp.setCuit_cuil(clientAll.getCuitCuil());
        clientResp.setFirstName(clientAll.getFirstName());
        clientResp.setLastname(clientAll.getLastName());
        clientResp.setSocial_reasons(clientAll.getSocialReason());
        clientResp.setEmail(clientAll.getEmail());
        clientResp.setAddress(clientAll.getAddress());
        clientResp.setAlternative_adress(clientAll.getAlternativeAddress());
        clientResp.setCity(clientAll.getCity());
        clientResp.setCountry(clientAll.getCountry());
        clientResp.setZipcode(clientAll.getZipCode());
        clientResp.setArea_code(clientAll.getAreaCode());
        clientResp.setPhone(clientAll.getPhone());
        clientResp.setAlternative_phone(clientAll.getAlternativePhone());
        clientResp.setProduct_service(clientAll.getProductService());
        clientResp.setActive(clientAll.getActive());
        clientResp.setGender_id(clientAll.getGenderId().getId());
        clientResp.setCreated_at(clientAll.getCreatedAt());
        clientResp.setUpdated_at(clientAll.getUpdateAt());


    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public void isActive(Long id) {
        Client client = clientRepo.findById(id).orElse(null);
        client.setActive(!client.getActive());
        clientRepo.save(client);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ClientResponse> listarGenders(Long genders_id) {
/*
        return jdbcTemplate.query("SELECT \"id\", email, client_type, document_type, \"id_number\", cuit_cuil, first_name, last_name,\r\n" +
                        "	social_reason, address, alternative_address, city, country, zip_code, area_code,\r\n" +
                        " phone, alternative_phone, product_service, active, created_at, update_at, gender_id\r\n" +
                        "FROM clients where gender_id = " + genders_id +
                        " GROUP BY \"id\", email, client_type, document_type, \"id_number\", cuit_cuil, first_name, last_name,\r\n" +
                        "	social_reason, address, alternative_address, city, country, zip_code, area_code,\r\n" +
                        " phone, alternative_phone, product_service, active, created_at, update_at, gender_id",
                (rs, rowNum) -> new ClientResponse(rs.getLong("id"), rs.getLong("client_type"), rs.getLong("document_type"), rs.getString("id_number"), rs.getString("cuit_cuil"),
                        rs.getString("first_name"), rs.getString("last_name"), rs.getString("social_reason"), rs.getString("email"),
                        rs.getString("address"), rs.getString("alternative_address"),
                        rs.getLong("city"), rs.getLong("country"), rs.getString("zip_code"), rs.getString("area_code"), rs.getString("phone"),
                        rs.getString("alternative_phone"), rs.getString("product_service"), rs.getBoolean("active"), rs.getLong("gender_id"),
                        rs.getDate("created_at"), rs.getDate("update_at")));

*/
        Gender gender = genderRepo.findById(genders_id).orElse(null);
        List<Client> clients = clientRepo.findClientByGenderId(gender);
        List<ClientResponse> clientResponses = new ArrayList<ClientResponse>();
        for (Client client : clients) {
            clientResponses.add(new ClientResponse(client));
        }
        return clientResponses;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<ClientResponse> listaClientActive(Boolean active) {

        List<Client> clients = clientRepo.findClientByActive(active);
        List<ClientResponse> clientResponses = new ArrayList<ClientResponse>();
        for (Client client : clients) {
            clientResponses.add(new ClientResponse(client));
        }
        return clientResponses;
    }
/*        return jdbcTemplate.query("SELECT \"id\", email, client_type, document_type, \"id_number\", cuit_cuil, first_name, last_name,\r\n" +
                        "	social_reason, address, alternative_address, city, country, zip_code, area_code,\r\n" +
                        " phone, alternative_phone, product_service, active, created_at, update_at, gender_id\r\n" +
                        "FROM clients where active= '" + active + "'" +
                        "GROUP BY \"id\", email, client_type, document_type, \"id_number\", cuit_cuil, first_name, last_name,\r\n" +
                        "	social_reason, clients.address, alternative_address, city, country, zip_code, area_code,\r\n" +
                        " phone, alternative_phone, product_service, active, created_at, update_at, gender_id",
                (rs, rowNum) -> new ClientResponse(rs.getLong("id"), rs.getLong("client_type"), rs.getLong("document_type"), rs.getString("id_number"), rs.getString("cuit_cuil"),
                        rs.getString("first_name"), rs.getString("last_name"), rs.getString("social_reason"), rs.getString("email"),
                        rs.getString("address"), rs.getString("alternative_address"),
                        rs.getLong("city"), rs.getLong("country"), rs.getString("zip_code"), rs.getString("area_code"), rs.getString("phone"),
                        rs.getString("alternative_phone"), rs.getString("product_service"), rs.getBoolean("active"), rs.getLong("gender_id"),
                        rs.getDate("created_at"), rs.getDate("update_at")));
    }*/
}
