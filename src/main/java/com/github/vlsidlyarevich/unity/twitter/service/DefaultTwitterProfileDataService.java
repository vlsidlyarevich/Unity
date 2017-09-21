package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.twitter.exception.TwitterServiceException;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterProfileData;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterSubscriptionData;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTwitterProfileDataService implements TwitterProfileDataService {

    private static final Long CURSOR = -1L;

    private final Mapper mapper;
    private final Twitter twitter;

    @Override
    public Optional<TwitterProfileData> getData(final String username) {
        return Optional.ofNullable(username)
                .map(usrname -> {
                    final TwitterProfileData profileData = getTwitterUser(username);
                    final List<TwitterSubscriptionData> subscriptions =
                            subscriptions(username)
                                    .map(subscr -> mapper.map(subscr, TwitterSubscriptionData.class))
                                    .collect(Collectors.toList());

                    profileData.setSubscriptions(subscriptions);

                    return Optional.of(profileData);
                }).orElseThrow(() -> new IllegalArgumentException("Twitter username should not be empty"));
    }

    private TwitterProfileData getTwitterUser(final String username) {
        try {
            return mapper.map(twitter.users().showUser(username), TwitterProfileData.class);
        } catch (final TwitterException e) {
            throw new TwitterServiceException(e);
        }
    }

    private Stream<User> subscriptions(final String username) {
        try {
            final IDs subscriptionsIDs = twitter.getFriendsIDs(username, CURSOR);
            return twitter.lookupUsers(subscriptionsIDs.getIDs()).stream();
        } catch (final TwitterException e) {
            throw new TwitterServiceException(e);
        }
    }
}