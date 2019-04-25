package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorDbo;

import javax.transaction.Transactional;

@Transactional
public interface DoctorRepository extends CustomRepository<DoctorDbo, Long> {
}