package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.constant.Topic;
import com.example.demo.entity.MessageDTO;
import com.example.demo.entity.StatisticDTO;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.StatisticRepositoy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PollingService {
    

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MessageRepository messageRepository;
    private final StatisticRepositoy statisticRepositoy;


    @Scheduled(fixedDelay = 15000)
    public void delete(){
        List<MessageDTO> messageDTOs = messageRepository.findByStatus(true);
        messageRepository.deleteAllInBatch(messageDTOs);

        List<StatisticDTO> statisticDTOs = statisticRepositoy.findByStatus(true);
        statisticRepositoy.deleteAllInBatch(statisticDTOs);
    }

    @Scheduled(fixedDelay = 1000)
    public void producer(){
        List<MessageDTO> messageDTOs = messageRepository.findByStatus(false);

        messageDTOs.forEach(System.out::println);


        messageDTOs.forEach( e -> {
            kafkaTemplate.send(Topic.NOTIFICATION.name(), e).whenComplete((result, ex) -> {
                if (ex == null) {
                    // ON SUCCESS

                    logger.info("Partition",String.valueOf(result.getRecordMetadata().partition()));
                    logger.info("Resend Message Successfully", e.getId());

                    e.setStatus(true);
                    messageRepository.save(e);

                } else {
                    // ON FAILURE
                    // ex.printStackTrace();
                    logger.info("FAIL", ex);
                }
            });
        });



        List<StatisticDTO> statisticDTOs = statisticRepositoy.findByStatus(false);

        statisticDTOs.forEach( e -> {
            kafkaTemplate.send(Topic.STATISTIC.name(), e).whenComplete((result, ex) -> {
                if (ex == null) {
                    // ON SUCCESS

                    logger.info("Partition",String.valueOf(result.getRecordMetadata().partition()));
                    logger.info("Resend statistic Successfully", e.getId());

                    e.setStatus(true);
                    statisticRepositoy.save(e);

                } else {
                    // ON FAILURE
                    // ex.printStackTrace();
                    logger.info("FAIL", ex);
                }
            });
        });
    }

}
