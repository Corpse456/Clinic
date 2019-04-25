package by.gp.clinic.repository;

import by.gp.clinic.dbo.PatientDbo;

import javax.transaction.Transactional;

@Transactional
public interface PatientRepository extends CustomRepository<PatientDbo, Long> {
}