package com.jobster.website.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "chat"
)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "creation_datetime")
    @NotNull
    private Timestamp creationDatetime;

    @ManyToOne
    @JoinColumn(
            name = "employee_id"
    )
    private Employee employeeChatOwner;

    @ManyToOne
    @JoinColumn(
            name = "employer_id"
    )
    private Employee employerChatOwner;

    @OneToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

}
