package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DTOS.RegisterDirectionDTO;
import med.voll.api.domain.medico.MedicoClass;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.DTOS.DataResponseMedicDTO;
import med.voll.api.domain.medico.DTOS.GetDataMedicDTO;
import med.voll.api.domain.medico.DTOS.RegisterDataMedicDTO;
import med.voll.api.domain.medico.DTOS.UpdateInfoMedicDTO;

import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medic")
public class RegisterMedicController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/RegisterNewMedic")
    public ResponseEntity<DataResponseMedicDTO> RegisterNewMedic(@RequestBody @Valid RegisterDataMedicDTO DatosMedico,
                                           UriComponentsBuilder uriComponentsBuilder) {
        MedicoClass medico = medicoRepository.save(new MedicoClass(DatosMedico));
        DataResponseMedicDTO response = new DataResponseMedicDTO(
                        medico.getId(),
                        medico.getNombre(),
                        medico.getApellido(),
                        medico.getDocumento(),
                        medico.getTelefono(),
                        new RegisterDirectionDTO(
                                medico.getDireccion().getCalle(),
                                medico.getDireccion().getDistrito(),
                                medico.getDireccion().getCiudad(),
                                medico.getDireccion().getNumero(),
                                medico.getDireccion().getComplemento()
                        ));

        /* Retorna codigo 201 Created */
        URI url = uriComponentsBuilder.path("medic/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(response);

    }

    @GetMapping
    public ResponseEntity<Page<GetDataMedicDTO>> GetListMedic(@PageableDefault(size = 10) Pageable pagination) {
        /* return medicoRepository.findAll(pagination).map(GetDataMedicDTO::new); -> trae todos los datos en la base de datos */
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pagination).map(GetDataMedicDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseMedicDTO> UpdateInfoMedic(@RequestBody @Valid UpdateInfoMedicDTO updateMedic) {
        MedicoClass medico = medicoRepository.getReferenceById(updateMedic.id());
        medico.updateInfo(updateMedic);
        return ResponseEntity.ok(
        new DataResponseMedicDTO(
                medico.getId(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getDocumento(),
                medico.getTelefono(),
                new RegisterDirectionDTO(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()
                )));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Any> updateStatusMedic(@PathVariable Long id) {
        MedicoClass medico = medicoRepository.getReferenceById(id);
        medico.changeStatusMedic();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DataResponseMedicDTO> returnMedicByID(@PathVariable Long id) {
        MedicoClass medico = medicoRepository.getReferenceById(id);
        var dataMedic = new DataResponseMedicDTO(
                        medico.getId(),
                        medico.getNombre(),
                        medico.getApellido(),
                        medico.getDocumento(),
                        medico.getTelefono(),
                        new RegisterDirectionDTO(
                                medico.getDireccion().getCalle(),
                                medico.getDireccion().getDistrito(),
                                medico.getDireccion().getCiudad(),
                                medico.getDireccion().getNumero(),
                                medico.getDireccion().getComplemento()
                        ));
        return ResponseEntity.ok(dataMedic);
    }



   /* Borrar dato definitivamente en la base de datos
   public void DeleteMedic(@PathVariable Long id) {
        MedicoClass medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/


}
