package br.com.southsystem.workshop.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class CpfService {
    private final Map<String, String> cpfs = new HashMap<>();

    public CpfService() {
        cpfs.put("39432953213", "inacio");
        cpfs.put("42156854213", "crystian");
        cpfs.put("93524676515", "marlon");
        cpfs.put("33563309709", "rafael");
    }

    @Deprecated
    public Optional<Map<String, String>> findByName(String name) {
        if (cpfs.containsKey(name)) {
            final Map<String, String> result = new HashMap<>();
            result.put("name", name);
            result.put("cpf", cpfs.get(name));
            return Optional.of(result);
        }

        return Optional.empty();
    }

    public Optional<Map<String, String>> findByCpf(String cpf) {
        if (cpfs.containsKey(cpf)) {
            final Map<String, String> result = new HashMap<>();
            result.put("cpf", cpf);
            result.put("name", cpfs.get(cpf));
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
