package com.github.vlsidlyarevich.unity;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.domain.model.SocialUserConnection;
import com.github.vlsidlyarevich.unity.domain.model.TwitterProfile;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.domain.model.UserSocial;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterPopularProfile;
import com.github.vlsidlyarevich.unity.web.security.model.Authority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class TestUtils {

    private TestUtils() {

    }

    public static UserAnalytics createUserAnalytics() {
        final List<AnalysisReport> reports = new ArrayList<>();
        reports.add(createAnalysisReport());

        return new UserAnalytics(TestRandomUtils.getRandomString(8), reports);
    }

    public static AnalysisReport createAnalysisReport() {
        return new AnalysisReport(null, new Date(), 1L);
    }

    public static User createUser() {
        final List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);

        return User.builder()
                .authorities(authorities)
                .username(TestRandomUtils.getRandomString(8))
                .password(TestRandomUtils.getRandomString(8))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .credentialsNonExpired(true)
                .build();
    }

    public static User createAdmin() {
        final List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_ADMIN);

        return User.builder()
                .authorities(authorities)
                .username(TestRandomUtils.getRandomString(8))
                .password(TestRandomUtils.getRandomString(8))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .credentialsNonExpired(true)
                .build();
    }

    public static UserSocial createUserSocial() {
        return UserSocial.builder()
                .firstName(TestRandomUtils.getRandomString(8))
                .lastName(TestRandomUtils.getRandomString(8))
                .email(TestRandomUtils.getRandomString(8))
                .skype(TestRandomUtils.getRandomString(8))
                .additional(TestRandomUtils.getRandomString(8))
                .image(TestRandomUtils.getRandomString(8))
                .build();
    }

    public static SocialUserConnection createSocialConnection() {
        return SocialUserConnection.builder()
                .userId(TestRandomUtils.getRandomString(8))
                .providerUserId(TestRandomUtils.getRandomString(8))
                .providerId(TestRandomUtils.getRandomString(8))
                .build();
    }

    public static TwitterPopularProfile createPopularProfile() {
        return TwitterPopularProfile.builder()
                .name(TestRandomUtils.getRandomString(8))
                .url(TestRandomUtils.getRandomString(8))
                .tags(new ArrayList<String>() {{
                    add(TestRandomUtils.getRandomString(8));
                }})
                .build();
    }

    public static TwitterProfile createTwitterProfile() {
        TwitterProfile twitterProfile = new TwitterProfile();
        twitterProfile.setName(TestRandomUtils.getRandomString(8));
        twitterProfile.setTags(new ArrayList<String>() {{
            add(TestRandomUtils.getRandomString(8));
        }});
        twitterProfile.setUrl(TestRandomUtils.getRandomString(8));

        return twitterProfile;
    }

    public static TwitterProfile createTwitterProfile(final TwitterPopularProfile popularProfile) {
        TwitterProfile twitterProfile = new TwitterProfile();
        twitterProfile.setName(popularProfile.getName());
        twitterProfile.setTags(popularProfile.getTags());
        twitterProfile.setUrl(popularProfile.getUrl());

        return twitterProfile;
    }
}
