using Microsoft.EntityFrameworkCore.Metadata.Conventions;
using System.ComponentModel.DataAnnotations;

namespace BookingApplication.Models {
    public class BookReservation {
        [Key]
        public Guid Id { get; set; }
        public Guid ReservationId { get; set; }
        public Reservation? Reservation { get; set; }
        public Guid BookingListId { get; set; }
        public BookingList? BookingList { get; set; }
        public int NumberOfNights { get; set; }
    }
}
