using Lab1.Web.Models.Domain;

namespace Lab1.Web.Models.DTO
{
    public class TicketsDTO
    {
        public List<Ticket>? Tickets{ get; set; }
        public int TotalPrice { get; set; }
    }
}
