using Lab1Guided.Models.Identity;

namespace Lab1Guided.Models.Domain
{
    public class Ticket
    {
        public Guid Id { get; set; }
        public string NumberOfPeople { get; set; }
        public ApplicationUser? User { get; set; }
        public string UserId { get; set; }
        public ConcertPlay? ConcertPlay { get; set; }
        public Guid ConcertPlayId { get; set; }
    }
}
