package com.example.chineseholiday.data.db

import com.example.chineseholiday.data.model.CalendarType
import com.example.chineseholiday.data.model.Holiday
import com.example.chineseholiday.data.model.HolidayType

/**
 * 节日数据初始化器
 */
object HolidayDataInitializer {
    
    /**
     * 获取默认的节日列表数据
     */
    fun getDefaultHolidays(): List<Holiday> {
        val holidays = mutableListOf<Holiday>()
        
        // 添加法定节假日
        holidays.add(
            Holiday(
                name = "元旦",
                month = 1,
                day = 1,
                type = HolidayType.LEGAL,
                calendarType = CalendarType.SOLAR,
                description = "元旦是公历新年，也是世界多数国家通称的"新年"。元，谓"始"，凡数之始称为"元"；旦，谓"日子"，"天明"，"早晨"。元旦，即意味着新的一年的第一天。",
                history = "元旦历史上有多种含义。在隋唐以前，元旦是指农历正月初一，俗称"春节"；唐代以后，元旦又指农历正月初一，正月初一称为"元正""三元"（天元、地元、人元，即一年、一月、一日）；明清时期，元旦多指农历正月初一；中华民国成立后，元旦则被规定为农历正月初一；1949年中华人民共和国成立后，国家将公历的1月1日定为元旦，农历的正月初一定为春节。",
                customs = "世界多数国家和地区过公历新年，放假一天。我国大陆、香港、澳门和少数民族地区放假三天。",
                isVacation = true
            )
        )
        
        holidays.add(
            Holiday(
                name = "春节",
                month = 1,
                day = 1,
                type = HolidayType.TRADITIONAL,
                calendarType = CalendarType.LUNAR,
                description = "春节，即中国农历新年，俗称新春、新岁，口头上又称过年、过大年。春节历史悠久，由上古时代岁首祈年祭祀演变而来。万物本乎天、人本乎祖，祈年祭祀、敬天法祖，报本反始也。春节的起源蕴含着深邃的民族文化内涵，在传承发展中承载了丰厚的历史文化底蕴。",
                history = "春节起源于殷商时期年头岁尾的祭神祭祖活动。在中国人的传统观念中，春节是一年的开始，春回大地，象征生机与活力，也是祭祀祖先、祈福禳灾、增强亲情和人际关系的时间，是中华民族最隆重的传统佳节。",
                customs = "春节期间有贴春联、贴福字、贴窗花、挂灯笼、祭祖、守岁、吃团圆饭、吃饺子、穿新衣、敬长辈、给压岁钱、燃放鞭炮烟花、舞龙舞狮等习俗。",
                isVacation = true
            )
        )
        
        holidays.add(
            Holiday(
                name = "清明节",
                month = 4,
                day = 5,
                type = HolidayType.TRADITIONAL,
                calendarType = CalendarType.SOLAR,
                description = "清明节，又称踏青节、寒食节、祭祖节等，是中国重要的传统节日之一。按阳历日期，它一般在每年的4月4日至6日之间，清明节是传统的重大春祭节日。",
                history = "清明节源自上古时代的祖先信仰与春祭礼俗，兼具自然与人文两大内涵，既是自然节气点，也是传统节日。清明节是一个集祭祀祖先、踏青郊游、社交娱乐为一体的综合性民俗节日。",
                customs = "清明节传统习俗有祭拜祖先、扫墓踏青、植树、放风筝等。节日期间，人们通常会回到家乡，参加家族的祭祖活动，缅怀逝去的亲人。",
                isVacation = true
            )
        )
        
        // 添加更多节日...
        holidays.add(
            Holiday(
                name = "劳动节",
                month = 5,
                day = 1,
                type = HolidayType.LEGAL,
                calendarType = CalendarType.SOLAR,
                description = "国际劳动节又称"五一国际劳动节"、"国际示威游行日"，是世界上80多个国家的全国性节日。定在每年的五月一日。它是全世界劳动人民共同拥有的节日。",
                history = "1889年7月，由恩格斯领导的第二国际在巴黎举行代表大会，会议通过决议，规定1890年5月1日国际劳动者举行游行，并决定把5月1日这一天定为国际劳动节。中国于1949年12月将5月1日定为法定的劳动节。",
                customs = "世界多数国家都将这一天定为法定假日。中国在1999年起，劳动节放假3天。",
                isVacation = true
            )
        )
        
        holidays.add(
            Holiday(
                name = "端午节",
                month = 5,
                day = 5,
                type = HolidayType.TRADITIONAL,
                calendarType = CalendarType.LUNAR,
                description = "端午节，为每年农历五月初五。其起源与纪念屈原密切相关，端午节是流行于中国以及汉字文化圈诸国的传统文化节日。",
                history = "端午节起源于中国，已有2000多年历史，最初是中国南方的吴越地区用于祛病防疫的节日，后来人们纷纷在龙舟节拾粽子和放艾条驱蚊。因传说战国时期的楚国诗人屈原在五月五日跳汨罗江自尽，后人亦将龙舟节作为纪念屈原的节日。",
                customs = "端午节的习俗主要有吃粽子、赛龙舟、插艾叶、挂菖蒲、戴香囊等。",
                isVacation = true
            )
        )
        
        holidays.add(
            Holiday(
                name = "中秋节",
                month = 8,
                day = 15,
                type = HolidayType.TRADITIONAL,
                calendarType = CalendarType.LUNAR,
                description = "中秋节，又称祭月节、月光诞、月夕、秋节、仲秋节、拜月节等，是中国民间的传统节日。中秋节源自天象崇拜，由上古时代秋夕祭月演变而来。",
                history = "中秋节始于唐朝初年，盛行于宋朝，至明清时，已与元旦齐名，成为中国的主要节日之一。中秋节起源于对月的崇拜，后来逐渐演变成团圆和祭祀的象征。",
                customs = "中秋节的习俗主要有赏月、吃月饼、赏桂花、饮桂花酒、玩花灯、祭月等民俗活动。",
                isVacation = true
            )
        )
        
        holidays.add(
            Holiday(
                name = "国庆节",
                month = 10,
                day = 1,
                type = HolidayType.LEGAL,
                calendarType = CalendarType.SOLAR,
                description = "国庆节是中华人民共和国成立的纪念日，定于每年公历10月1日。1949年10月1日，是中华人民共和国中央人民政府成立宣告典礼举行的日子。",
                history = "1949年10月1日，在北京天安门广场举行的中华人民共和国开国大典上，毛泽东主席向全世界庄严宣告中华人民共和国中央人民政府成立了。从1950年起，每年的10月1日被定为国庆节。",
                customs = "每年的国庆节，天安门广场都会举行升国旗仪式，人们观看阅兵式、文艺演出等庆祝活动。全国放假7天，被称为"黄金周"。",
                isVacation = true
            )
        )
        
        // 返回节日列表
        return holidays
    }
} 