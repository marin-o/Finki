using System.ComponentModel.DataAnnotations;
using BA.Domain.Identity;

namespace BA.Domain.Models
{
    public class BookReservation : BaseEntity
    {
       
        
        [Required]
        public int Number_Of_Nights { get; set; }

        public Reservation? Reservation { get; set; }
        public Guid? ReservationId { get; set; }

        public BookingList? BookingList { get; set; }
        public Guid? BookingListId { get; set; }

        public BookingApplicationUser? User { get; set; }
        public string? UserId { get; set; }

    }
}
