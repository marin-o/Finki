using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using IntegratedSystems.Service.Interface;


namespace IntegratedSystems.Service.Implementation
{
    public class VaccinationCenterServiceImpl : IVaccinationCenterService
    {

        private readonly IRepository<VaccinationCenter> vaccinationCenterRepository;

        public VaccinationCenterServiceImpl(IRepository<VaccinationCenter> repository)
        {
            vaccinationCenterRepository = repository;
        }

        public VaccinationCenter CreateNewVaccinationCenter(VaccinationCenter vaccinationCenter)
        {
            return vaccinationCenterRepository.Insert(vaccinationCenter);
        }

        public VaccinationCenter DeleteVaccinationCenter(Guid id)
        {
            var vaccCenter = this.GetVaccinationCenterById(id);
            return vaccinationCenterRepository.Delete(vaccCenter);
        }

        public VaccinationCenter GetVaccinationCenterById(Guid? id)
        {
            return vaccinationCenterRepository.Get(id);
        }

        public List<VaccinationCenter> GetVaccinationCenters()
        {
            return vaccinationCenterRepository.GetAll().ToList();
        }

        public void LowerCapacity(Guid id)
        {
            var vaccCenter = this.GetVaccinationCenterById(id);
            vaccCenter.MaxCapacity--;
            this.UpdateVaccinationCenter(vaccCenter);
        }

        public VaccinationCenter UpdateVaccinationCenter(VaccinationCenter vaccinationCenter)
        {
            return vaccinationCenterRepository.Update(vaccinationCenter);
        }
    }
}
