using BA.Domain.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Repository.Interface
{
    public interface IBookingListRepository
    {
        List<BookingList> GetAllOrders();
        BookingList GetDetailsForOrder(BaseEntity id);
    }
}
