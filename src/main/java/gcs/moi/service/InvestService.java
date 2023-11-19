package gcs.moi.service;

import gcs.moi.repository.InvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestService {

    private final InvestRepository investRepository;

    public void save() {

    }
}
