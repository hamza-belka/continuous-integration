package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		try {
			l.info("*****Begin In ajouterEntreprise()***** : ");
			l.debug("Je vais lancer l ajout d entreprsie.");
		
				entrepriseRepoistory.save(entreprise);
				l.debug("Je viens de lancer la l'ajout de " + entreprise.getId());
				l.debug("******* Je viens de finir l'ajout ********");
				l.info("Out ajouterEntreprise() without errors.");
}
		catch (Exception e) {
				l.error("erreur dans ajouterEntreprise() :"+e);
		}
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		try {
		l.info("*****Begin In ajouterDepartement()***** : ");
		l.debug("Je vais lancer l ajout de departement.");
		deptRepoistory.save(dep);
		l.debug("******* Je viens de finir l'ajout ********");
		l.info("Out ajouterDepartement() without errors.");
		}
		catch (Exception e) {
			l.error("erreur dans ajouterDepartement() :"+e);
	}
		
		return dep.getId();
		
}
	
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		l.info("*****Begin In affecterDepartementAEntreprise()***** : ");
		l.debug("Je vais lancer l laffection .");
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				
				l.debug("******* Je viens de finir l'affectation ********");

		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.info("*****Begin In getAllDepartementsNamesByEntreprise()***** : ");
		l.debug("Je vais lancer le get  .");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		l.debug("******* Je viens de finir l'affectation ********");

		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.info("*****Begin In deleteEntrepriseById()***** : ");
		l.debug("Je vais lancer le delete  .");
		
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
		
		l.debug("******* Je viens de finir entreprise delete ********");

	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.info("*****Begin In deleteDepartementById()***** : ");
		l.debug("Je vais lancer le delete  .");
		
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
		
		l.debug("******* Je viens de finir departement delete ********");

	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		
		return entrepriseRepoistory.findById(entrepriseId).get();	
		
	}

}
