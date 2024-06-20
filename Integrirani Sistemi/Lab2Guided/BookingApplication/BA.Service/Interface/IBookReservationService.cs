using BA.Domain.DTO;
using BA.Domain.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Service.Interface
{
    internal interface IBookReservationService
    {
        BookReservationDTO getShoppingCartInfo(string userId);
        bool deleteProductFromShoppingCart(string userId, Guid productId);
        bool order(string userId);
        bool AddToShoppingConfirmed(BookReservation model, string userId);
    }
}
