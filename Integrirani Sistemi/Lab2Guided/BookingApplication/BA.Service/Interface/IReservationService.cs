using BA.Domain.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Service.Interface
{
    public interface IReservationService
    {
        List<Reservation> GetAllProducts();
        Reservation GetDetailsForProduct(Guid? id);
        void CreateNewProduct(Reservation p);
        void UpdateExistingProduct(Reservation p);
        void DeleteProduct(Guid id);
    }
}
