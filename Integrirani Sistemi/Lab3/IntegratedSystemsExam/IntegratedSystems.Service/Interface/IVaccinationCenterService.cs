using IntegratedSystems.Domain.Domain_Models;

namespace IntegratedSystems.Service.Interface
{
    public interface IVaccinationCenterService
    {
        public List<VaccinationCenter> GetVaccinationCenters();
        public VaccinationCenter GetVaccinationCenterById(Guid? id);
        public VaccinationCenter CreateNewVaccinationCenter(VaccinationCenter vaccinationCenter);
        public VaccinationCenter UpdateVaccinationCenter(VaccinationCenter vaccinationCenter);
        public VaccinationCenter DeleteVaccinationCenter(Guid id);

        public void LowerCapacity(Guid id);
    }
}
