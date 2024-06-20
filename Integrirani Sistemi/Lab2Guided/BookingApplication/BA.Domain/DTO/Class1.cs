using BA.Domain.Identity;
using BA.Domain.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Domain.DTO
{
    public class BookReservationDTO
    {
        public int Number_Of_Nights { get; set; }

        public Reservation? Reservation { get; set; }
        public Guid? ReservationId { get; set; }

        public BookingList? BookingList { get; set; }
        public Guid? BookingListId { get; set; }

        public BookingApplicationUser? User { get; set; }
        public string? UserId { get; set; }
    }
}
