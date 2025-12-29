package com.example.finalse.mappers;

import com.example.finalse.dtos.PaymentsRequestDto;
import com.example.finalse.dtos.PaymentsResponseDto;
import com.example.finalse.entities.Payments;
import com.example.finalse.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T02:08:42+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class PaymentsMapperImpl implements PaymentsMapper {

    @Override
    public PaymentsResponseDto toDto(Payments payments) {
        if ( payments == null ) {
            return null;
        }

        PaymentsResponseDto.PaymentsResponseDtoBuilder paymentsResponseDto = PaymentsResponseDto.builder();

        paymentsResponseDto.user_id( paymentsUserId( payments ) );
        paymentsResponseDto.id( payments.getId() );
        paymentsResponseDto.date( payments.getDate() );
        paymentsResponseDto.value( payments.getValue() );
        paymentsResponseDto.status( payments.getStatus() );

        return paymentsResponseDto.build();
    }

    @Override
    public Payments toEntity(PaymentsRequestDto paymentsRequestDto) {
        if ( paymentsRequestDto == null ) {
            return null;
        }

        Payments.PaymentsBuilder payments = Payments.builder();

        payments.user( paymentsRequestDtoToUser( paymentsRequestDto ) );
        payments.date( paymentsRequestDto.getDate() );
        payments.value( paymentsRequestDto.getValue() );
        payments.status( paymentsRequestDto.getStatus() );

        return payments.build();
    }

    @Override
    public List<PaymentsResponseDto> toDtoList(List<Payments> paymentsList) {
        if ( paymentsList == null ) {
            return null;
        }

        List<PaymentsResponseDto> list = new ArrayList<PaymentsResponseDto>( paymentsList.size() );
        for ( Payments payments : paymentsList ) {
            list.add( toDto( payments ) );
        }

        return list;
    }

    private Long paymentsUserId(Payments payments) {
        if ( payments == null ) {
            return null;
        }
        User user = payments.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User paymentsRequestDtoToUser(PaymentsRequestDto paymentsRequestDto) {
        if ( paymentsRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( paymentsRequestDto.getUser_id() );

        return user.build();
    }
}
